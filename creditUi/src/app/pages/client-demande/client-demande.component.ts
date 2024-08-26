import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator'
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { MatTableDataSource,MatTableModule} from '@angular/material/table';
import { Demande } from '../../services/models/demande';
import { DemandeService } from '../../services/demande.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-client-demande',
  standalone: true,
  imports: [MatTableModule, MatButtonModule, MatIconModule,MatPaginatorModule,CommonModule],
  animations: [
    trigger('detailExpand', [
      state('collapsed,void', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
  templateUrl: './client-demande.component.html',
  styleUrl: './client-demande.component.css'
})
export class ClientDemandeComponent implements OnInit{
  demandes: Demande[] = [];

  
  columnsToDisplay = [
      'montant', 'montantARembourser', 'unite',
    'nbrDechenance', 'observation', 'status',  'dateDemande'
  ];
  columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];
  dataSource = new MatTableDataSource<Demande>(this.demandes);
  expandedElement: Demande | null | undefined;

  @ViewChild(MatPaginator) paginator: MatPaginator = null!;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  

  constructor(private demandeService: DemandeService) { }
  ngOnInit(): void {
    const userId = localStorage.getItem('userId');
    // add id from local storge
    if (userId !== null) {
      this.demandeService.getDemandesById(userId).subscribe(data => {
        this.demandes = data;
        this.dataSource.data = this.demandes;
        console.log(data);
      });
    } else {
      // Handle the case where userId is null
      console.error('User ID is not found in localStorage');
    }
  }
  
 

}

