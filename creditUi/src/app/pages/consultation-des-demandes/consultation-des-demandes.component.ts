import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator'
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { MatTableDataSource,MatTableModule} from '@angular/material/table';
import { Demande } from '../../services/models/demande';
import { DemandeService } from '../../services/demande.service';
import { CommonModule } from '@angular/common';
import { PdfService } from '../../services/pdf.service';


@Component({
  selector: 'app-consultation-des-demandes',
  standalone: true,
  imports: [MatTableModule, MatButtonModule, MatIconModule,MatPaginatorModule,CommonModule],
  animations: [
    trigger('detailExpand', [
      state('collapsed,void', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],

  templateUrl: './consultation-des-demandes.component.html',
  styleUrl: './consultation-des-demandes.component.css'
})
export class ConsultationDesDemandesComponent implements OnInit {

  demandes: Demande[] = [];

  
  columnsToDisplay = [
      'montant', 'montantARembourser', 'unite',
    'nbrDechenance', 'observation', 'status',  'dateDemande',
    'userName', 'typeCredit'
  ];
  columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];
  dataSource = new MatTableDataSource<Demande>(this.demandes);
  expandedElement: Demande | null | undefined;

  @ViewChild(MatPaginator) paginator: MatPaginator = null!;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  constructor(private demandeService: DemandeService,private pdfService: PdfService ) { }
  ngOnInit(): void {
    this.demandeService.getDemandes().subscribe(data => {
      this.demandes = data;
      this.dataSource.data = this.demandes;
      console.log(data);
    });
  }
  
  acceptDemande(id: number) {
    this.demandeService.updateDemandeValide(id).subscribe(
      () => {
        // Handle successful update
        console.log(`Demande with id ${id} has been accepted.`);
        
        const acceptedDemande = this.demandes.find(demande => demande.id === id);
        
        if (acceptedDemande) {
          // Generate PDF for the accepted demande
          this.pdfService.generatePdf(acceptedDemande, `demande_${id}`);
          
          // Optionally, refresh the data or update UI accordingly
          this.ngOnInit(); // Ensure ngOnInit() properly refreshes data
        }
      },
      error => {
        // Handle error
        console.error('Error updating demande', error);
      }
    );
  }
  rejectDemande(id: number) {
    this.demandeService.updateDemandeRejete(id).subscribe(() => {
      // Handle successful update
      console.log(`Demande with id ${id} has been rejeté.`);
      // Optionally, refresh the data or update UI accordingly
      this.ngOnInit();
    }, error => {
      // Handle error
      console.error('Error updating demande', error);
    });
  }

}

