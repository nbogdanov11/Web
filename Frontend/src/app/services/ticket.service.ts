import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private baseUrl = environment.baseUrl + 'ticket';

  constructor(private http: HttpClient) { }

  public reserveTicket(body): Observable<any> {
    return this.http.post(this.baseUrl, body);
  }

  public payTicket(id,body): Observable<any> {
    return this.http.put(this.baseUrl + `/pay/${id}/projection`, body);
  }

  public cancelTicket(id,body): Observable<any> {
    return this.http.put(this.baseUrl + `/cancel/${id}/projection`, body);
  }
}
