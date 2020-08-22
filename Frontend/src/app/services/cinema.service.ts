import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  private baseUrl = environment.baseUrl + 'cinema';

  constructor(private http: HttpClient) { }

  public createCinema(body): Observable<any> {
    return this.http.post(this.baseUrl, body);
  }

  public updateCinema(body): Observable<any> {
    return this.http.put(this.baseUrl, body);
  }

  public getCinemas(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  public getCinema(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}`);
  }

  public deleteCinema(id): Observable<any> {
    return this.http.delete(this.baseUrl + `/${id}`);
  }

  public getAllCinemasByManager(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}/manager`);
  }
}
