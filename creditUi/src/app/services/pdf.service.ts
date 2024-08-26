import { Injectable } from '@angular/core';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { Demande } from './models/demande';

@Injectable({
  providedIn: 'root'
})
export class PdfService {
  private fieldMapping: { [key: string]: string } = {
    'id': 'Numéro d\'identification',
    'clientId': 'Identifiant du client',
    'creditId': 'Identifiant de crédit',
    'montant': 'Montant',
    'montantARembourser': 'Montant à Rembourser',
    'unite': 'Unité',
    'nbrDechenance': 'Nombre de Déchéance',
    'observation': 'Observation',
    'status': 'Statut',
    'dateDemande': 'Date de Demande',
    'userName': 'Nom d\'Utilisateur',
    'typeCredit': 'Type de Crédit',
    'garanties': 'Garanties'
    // Add more mappings as needed
  };

  constructor() { }

  // Function to convert image URL to base64
  convertImageUrlToBase64(imgUrl: string, callback: (base64: string) => void) {
    const img = new Image();
    img.crossOrigin = 'Anonymous'; // Handle cross-origin issues

    img.onload = () => {
      const canvas = document.createElement('canvas');
      const ctx = canvas.getContext('2d');

      if (ctx) {
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.drawImage(img, 0, 0);

        const base64 = canvas.toDataURL('image/png'); // Convert image to base64
        callback(base64);
      }
    };

    img.src = imgUrl;
  }

  // Function to format 'garanties'
  private formatGaranties(garanties: any[]): string {
    if (!garanties || garanties.length === 0) {
      return 'Aucune garantie';
    }
    return garanties.map((g: { nature: any; type: any; valeur: any; devise: any; }) => 
      `Nature: ${g.nature}, Type: ${g.type}, Valeur: ${g.valeur}, Devise: ${g.devise}`
    ).join('\n');
  }

  // Function to generate PDF
  generatePdf(demande: Demande, fileName: string) {
    const doc = new jsPDF();

    // Image URL (local or remote)
    const imgUrl = 'assets/generale_tunisienne_informatique_cover.jpg'; // Replace with your actual image URL or path

    // Convert image and add it to the PDF
    this.convertImageUrlToBase64(imgUrl, (imgData) => {
      // Define image properties
      const imgWidth = 181;  // Width of the image in PDF units
      const imgHeight = 38;  // Height of the image in PDF units
      
      // Add image to PDF
      doc.addImage(imgData, 'PNG', 14, 14, imgWidth, imgHeight); // Adjust positioning and size as needed
      
      // Add title below the image
      doc.setFontSize(18);
      doc.text('Détails de la Demande', 14, imgHeight + 24); // Position the title below the image

      // Prepare data for the table
      const tableColumn = ["Champ", "Valeur"];
      const tableRows = Object.entries(demande).map(([key, value]) => {
        const fieldName = this.fieldMapping[key] || key;
        if (key === 'garanties') {
          return [fieldName, this.formatGaranties(value)];
        }
        return [fieldName, value?.toString() || 'N/A'];
      });

      // Add content using autoTable
      autoTable(doc, {
        startY: imgHeight + 30, // Position table below the title
        head: [tableColumn],
        body: tableRows,
      });

      // Save PDF
      doc.save(`${fileName}.pdf`);
    });
  }
}
