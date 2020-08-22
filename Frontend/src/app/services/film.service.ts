import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  private baseUrl = environment.baseUrl + 'film';

  constructor(private http: HttpClient) { }

  public createFilm(body): Observable<any> {
    return this.http.post(this.baseUrl, body);
  }

  public updateFilm(body): Observable<any> {
    return this.http.put(this.baseUrl, body);
  }

  public getFilms(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  public getFilm(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}`);
  }

  public deleteFilm(id): Observable<any> {
    return this.http.delete(this.baseUrl + `/${id}`);
  }
}
