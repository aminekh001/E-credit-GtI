import { Component, OnInit, ViewChild, TemplateRef, OnDestroy } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { Subscription } from 'rxjs';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { ClientDocument } from '../services/models/Client-Document';
import { garanties } from '../services/models/garanties';
import { Credit } from '../services/models/credit';
import { CreditService } from '../services/credit.service';
import { user } from '../services/models/user';
import { UserService } from '../services/user.service';
import { Demande } from '../services/models/demande';
import { DemandeRequest, DemandeService } from '../services/demande.service';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [
    CommonModule,  
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatRadioModule,
    MatSelectModule,
    MatTableModule,
    MatIconModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatExpansionModule
  ],
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit, OnDestroy {

  
  credits: Credit[] = [];
  selectedCredit: Credit | undefined;
  UserCompte: user[] = [{ id: '', bankAccountsList: [''] }];
  clientDocuments: ClientDocument[] = [{ docName: '', typeDoc: '', docPath: '' }];
  data: garanties[] = [];
  showPopup = false;
  newItem: garanties = { nature: '', type: '', valeur: '', devise: '' };
  firstFormGroup!: FormGroup;
  creditFormGroup!: FormGroup;
  guaranteeForm!: FormGroup;
  clientInfoForm!: FormGroup;
  observationForm!: FormGroup;
  interestRate!: number;
  repaymentAmount: number | null = null;
  isLinear = true;
  creditFormSubscription: Subscription | undefined;

  @ViewChild('guaranteeDialog') guaranteeDialog!: TemplateRef<any>;

  constructor(
    private _formBuilder: FormBuilder, 
    private dialog: MatDialog, 
    private creditService: CreditService, 
    private userService: UserService, 
    private demandeService: DemandeService
  ) {}

  ngOnInit(): void {
    this.creditService.getCredits().subscribe(data => {
      this.credits = data;
    });

    this.userService.getUserCompte(localStorage.getItem("userId")!).subscribe(data => {
      this.UserCompte = data;
    });

    this.firstFormGroup = this._formBuilder.group({
      credit: ['', Validators.required]
    });

    this.creditFormGroup = this._formBuilder.group({
      creditAmount: [25000, [Validators.required, Validators.min(1), Validators.max(50000)]],
      monthlyNumber: [12, [Validators.required, Validators.min(1), Validators.max(120)]],
      dueDates: ['Mensuelle', Validators.required],
    });

    this.guaranteeForm = this._formBuilder.group({
      nature: ['', Validators.required],
      type: ['', Validators.required],
      valeur: ['', Validators.required],
      devise: ['', Validators.required]
    });

    this.clientInfoForm = this._formBuilder.group({
      compte: ['', Validators.required],
      EntreeEnRelation: [null, Validators.required],
      par: ['', Validators.required]
    });

    this.observationForm = this._formBuilder.group({
      observation: ['', Validators.required]
    });

    this.creditFormSubscription = this.creditFormGroup.valueChanges.subscribe(() => {
      this.calculateRepayment();
    });
  }

  ngOnDestroy() {
    if (this.creditFormSubscription) {
      this.creditFormSubscription.unsubscribe();
    }
  }

  onNext(): void {
    const selectedCreditId = this.firstFormGroup.get('credit')?.value;

    if (selectedCreditId) {
      this.selectedCredit = this.credits?.find(c => c.id === selectedCreditId);
    } else {
      this.selectedCredit = undefined;
    }
    this.interestRate = this.selectedCredit?.interestRate ?? 0.1;
  }

  calculateRepayment() {
    const creditAmount = this.creditFormGroup.get('creditAmount')?.value;
    const monthlyNumber = this.creditFormGroup.get('monthlyNumber')?.value;

    if (creditAmount !== null && monthlyNumber !== null) {
      this.repaymentAmount = creditAmount + (creditAmount * this.interestRate);
      if (this.repaymentAmount !== null) {
        this.repaymentAmount = this.repaymentAmount / monthlyNumber;
      }
    } else {
      this.repaymentAmount = null;
    }
  }

  addData() {
    this.dialog.open(this.guaranteeDialog);
  }

  closeGuaranteeDialog() {
    this.dialog.closeAll();
    this.guaranteeForm.reset();
  }
  
  addGuarantee() {
    if (this.guaranteeForm.valid) {
      this.data = [...this.data, this.guaranteeForm.value]; // Ensure array reference changes
      this.closeGuaranteeDialog();
    } else {
      console.log('Form is invalid');
    }
  }

  deleteData(index: number) {
      if (index > -1) {
      this.data.splice(index, 1);
      this.data = [...this.data]; 
    }
    console.log(this.data)
  }

  onFileSelected(event: Event, index: number): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const fileName = this.cleanPath(file.name);
      this.clientDocuments = [
        ...this.clientDocuments.slice(0, index),
        {
          docName: fileName,
          typeDoc: file.type,
          docPath: `C:/EcreditData/${fileName}`,
          file: file
          
        },
        ...this.clientDocuments.slice(index + 1)
      ];

      if (index === this.clientDocuments.length - 1) {
        this.addNewRow();
      }
    }
  }

  addNewRow(): void {
    
  }

  removeDocument(index: number): void {
    this.clientDocuments.splice(index, 1);
    if (this.clientDocuments.length === 0) {
      this.addNewRow();
    }
  }

  clearFile(index: number): void {
    this.clientDocuments[index] = { docName: '', typeDoc: '', docPath: '' };
    this.clientDocuments = [...this.clientDocuments];
  }

  uploadFiles(): void {
    const filesToUpload = this.clientDocuments.filter(doc => doc.file !== null);
    console.log('Documents to upload:', filesToUpload);
  }

  hasFilesToUpload(): boolean {
    return this.clientDocuments.some(doc => doc.file !== null);
  }
  private cleanPath(fileName: string): string {
    return fileName.replace(/[^\w\-\.]/g, '');
  }
  
  submitForm() {
    if (
      this.firstFormGroup.valid &&
      this.creditFormGroup.valid &&
      this.clientInfoForm.valid &&
      this.observationForm.valid
    ) {
        const updatedClientDocuments = this.clientDocuments.map((doc) => ({
            ...doc,
            file: doc.file || null, // Set file to null if undefined
        }));

        const demande: DemandeRequest = {
            id: null,
            clientId: localStorage.getItem('userId')!,
            creditId: this.selectedCredit?.id ?? 0,
            montant: this.creditFormGroup.get('creditAmount')?.value,
            montantARembourser: this.repaymentAmount ?? 0,
            numCompte: this.clientInfoForm.get('compte')?.value,
            unite: this.creditFormGroup.get('dueDates')?.value,
            status: null,
            dateDemande: null,
            userName: null,
            typeCredit: null,
            nbrDechenance: this.creditFormGroup.get('monthlyNumber')?.value,
            observation: this.observationForm.get('observation')?.value,
            documents: updatedClientDocuments, // Use the updated client documents
            garanties: this.data,
        };

        console.log('Demande created with ID: null', demande);

        const formData = new FormData();
        formData.append('request', JSON.stringify(demande));

        const filesToUpload = this.clientDocuments
            .filter((doc) => doc.file !== null)
            .map((doc) => doc.file!);

        // Append files to FormData
        filesToUpload.forEach((file, index) => {
            formData.append('files', file); // Ensure key matches the backend expectation
        });

        // Call createDemande with both arguments
        this.demandeService.createDemande(demande, filesToUpload).subscribe(
            (response: number) => {
                console.log('Demande created with ID:', response);
            },
            (error: any) => {
                console.error('Error creating demande:', error);
            }
        );
    }
}
}
