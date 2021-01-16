import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Mensagens } from 'src/app/shared/utils/Mensagens';
import { Contato } from '../../model/Contato';
import { ContatoService } from '../../service/contato.service';
import { DetalheContatoComponent } from '../detalhe-contato/detalhe-contato.component';
 
@Component({
  selector: 'app-cadastro-contato',
  templateUrl: './cadastro-contato.component.html',
  styleUrls: ['./cadastro-contato.component.css']
})
export class CadastroContatoComponent implements OnInit {

 public formulario: FormGroup;
 public contatos: Contato[];
 public colunas = ['foto', 'id', 'nome', 'email', 'favorito'];
 public totalElementos = 0;
 public pagina = 0;
 public tamanho = 10;
 public pageSizeOptions: number[] = [10];

  constructor(private formBuilder: FormBuilder,
    private contatoService: ContatoService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.createForm();
    this.consultarContatos(this.pagina, this.tamanho);
  }

  public createForm(): void {
    this.formulario = this.formBuilder.group(
      {
        nome: [null, Validators.required],
        email: [null, [Validators.required,Validators.email]]
      }
    );
  }

  public consultarContatos(pagina = 0, tamanho = 10): void {
    this.contatoService.consultarContatos(pagina, tamanho).subscribe(
      response => {
        this.contatos = response.content;
        this.totalElementos = response.totalElements;
        this.pagina = response.number;
      }
    );
  }

  public favoritarContato(contato: Contato): void {
    this.contatoService.favoritarContato(contato).subscribe(
      response => {
        contato.favorito = !contato.favorito;
      }
    );
  }

  public cadastrar(): void {
    let contato = new Contato();
    contato.id = null;
    contato.nome = this.formulario.get('nome').value;
    contato.email = this.formulario.get('email').value;
    //const formValues = this.formulario.value;
    //const contato: Contato = new Contato(formValues.nome, formValues.email);
    this.contatoService.salvar(contato).subscribe(
      response => {
        this.consultarContatos();
        this.snackBar.open(Mensagens.MSG_CONTATO_ADICIONADO, Mensagens.SUCESSO, {
          duration: 2000
        })
        this.formulario.reset();
      }
    );
  }

  public uploadFoto(event, contato): void {
    const files = event.target.files;
    if(files) {
      const foto = files[0];
      const formData: FormData = new FormData();
      formData.append("foto", foto);
      this.contatoService.uploadFoto(contato, formData).subscribe(
        response => {
          this.consultarContatos();
        }
      )
    }
  } 

  public visualizarContato(contato: Contato): void {
    this.dialog.open(DetalheContatoComponent, {
      width: '400px',
      height: '450px',
      data: contato
    })
  }

  public paginar(event: PageEvent) : void {
    this.pagina = event.pageIndex;
    this.consultarContatos(this.pagina, this.tamanho);
  }

}
