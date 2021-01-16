import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Contato } from '../../model/Contato';

@Component({
  selector: 'app-detalhe-contato',
  templateUrl: './detalhe-contato.component.html',
  styleUrls: ['./detalhe-contato.component.css']
})
export class DetalheContatoComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DetalheContatoComponent>,
   @Inject(MAT_DIALOG_DATA) public contato: Contato) { }

  ngOnInit(): void {
  }

  public fechar(): void {
    this.dialogRef.close();
  }

}
