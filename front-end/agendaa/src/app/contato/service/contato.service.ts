import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Paginable } from 'src/app/shared/models/Paginable';
import { environment } from 'src/environments/environment';
import { Contato } from '../model/Contato';

@Injectable({
  providedIn: 'root'
})
export class ContatoService {

 public url: string = environment.apiBaseUrl;

  constructor(protected http: HttpClient) { }

  public salvar(contato: Contato): Observable<Contato> {
    return this.http.post<Contato>(this.url, contato);
  }

  public consultarContatos(page, size): Observable<Paginable> {
    const params = new HttpParams()
    .set('page', page)
    .set('size', size)
    return this.http.get<any>(`${this.url}?${params.toString()}`);
  }

  public favoritarContato(contato: Contato): Observable<any>{
    return this.http.patch<any>( `${this.url}/${contato.id}/favorito`, null );
  }

  public uploadFoto(contato: Contato, formData: FormData): Observable<any> {
    return this.http.put(`${this.url}/${contato.id}/foto`, formData, { responseType: 'blob' });
  }
}
