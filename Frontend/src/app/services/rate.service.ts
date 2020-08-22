import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RateService {

  private baseUrl = environment.baseUrl + 'rate';

  constructor(private http: HttpClient) { }

  public rateFilm(body): Observable<any> {
    return this.http.post(this.baseUrl, body);
  }

  public getAllRatedFilmsByViewer(id): Observable<any> {
    return this.http.get(this.baseUrl + `/rated-films/${id}/viewer`);
  }

  public getAllFilmsWhichCanBeRatedByViewer(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}/viewer`);
  }

  public getFilmsRating(id):Observable<any> {
    return this.http.get(this.baseUrl + `/rating/${id}/film`);
  }

  public deleteFilm(id): Observable<any> {
    return this.http.delete(this.baseUrl + `/${id}`);
  }
}
