import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectionService {

  private baseUrl = environment.baseUrl + 'projection';

  constructor(private http: HttpClient) { }

  public createProjection(body): Observable<any> {
    return this.http.post(this.baseUrl, body);
  }

  public getProjections(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  public getProjection(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}`);
  }

  public deleteProjection(id): Observable<any> {
    return this.http.delete(this.baseUrl + `/${id}`);
  }

  public getAllProjectionsByCinema(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}/cinema`);
  }

  public getAllProjectionsByFilm(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}/film`);
  }

  // public getAllPaidProjectionsByViewerWhichAreInTheFuture(id): Observable<any> {
  //   return this.http.get(this.baseUrl + `/future/paid/${id}/viewer`);
  // }

  // public getAllPaidProjectionsByViewerWhichAreInThePast(id): Observable<any> {
  //   return this.http.get(this.baseUrl + `/past/paid/${id}/viewer`);
  // }

  public getAllReservedProjectionsByViewer(id): Observable<any> {
    return this.http.get(this.baseUrl + `/reserved/${id}/viewer`);
  }

  public getProjectionsBySearch(filter = {}): Observable<any> {
    return this.http.get(this.baseUrl + `/search${this.buildFilterRequest(filter)}`);
  }

  private buildFilterRequest(filterObject: any): String {
    const values = Object.keys(filterObject).filter(filterValue => filterValue !== null || filterValue !== '');
    if(values.length === 0) {
      return '';
    }
    let filterQuery = '?';
    let counter;
    Object.keys(filterObject).forEach(x => {
      if(filterObject[x] !== null || filterObject[x] !== '') {
        let temp = '';
        if(counter === 0) {
          temp = '';
        } else {
          temp = '&';
        }
        filterQuery = filterQuery + temp + x + '=' + filterObject[x];
        counter = counter + 1;
      }
    })
    return filterQuery;
  }
}
