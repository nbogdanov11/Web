import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TheaterService {

  private baseUrl = environment.baseUrl + 'theater';

  constructor(private http: HttpClient) { }

  public createTheater(body): Observable<any> {
    return this.http.post(this.baseUrl, body);
  }

  public updateTheater(body): Observable<any> {
    return this.http.put(this.baseUrl, body);
  }

  public getTheater(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}`);
  }

  public deleteTheater(id): Observable<any> {
    return this.http.delete(this.baseUrl + `/${id}`);
  }

  public getAllTheatersByCinema(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}/cinema`);
  }

  public getAllTheatersByManager(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}/manager`);
  }
}
