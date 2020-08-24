import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.baseUrl + 'user';

  constructor(private http: HttpClient) { }

  public registerViewer(body): Observable<any> {
    return this.http.post(this.baseUrl + '/new-viewer', body);
  }

  public registerManager(body): Observable<any> {
    return this.http.post(this.baseUrl + '/new-manager', body);
  }

  public updateUser(body): Observable<any> {
    return this.http.put(this.baseUrl, body);
  }

  public login(body): Observable<any> {
    return this.http.post(this.baseUrl + '/login', body);
  }

  public getUsers(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  public getUser(id): Observable<any> {
    return this.http.get(this.baseUrl + `/${id}`);
  }

  public blockUser(id): Observable<any> {
    return this.http.delete(this.baseUrl + `/${id}`);
  }

  public getViewers(): Observable<any> {
    return this.http.get(this.baseUrl + '/viewers');
  }

  public getManagers(): Observable<any> {
    return this.http.get(this.baseUrl + '/managers');
  }

  public getInactiveManagers(): Observable<any> {
    return this.http.get(this.baseUrl + '/inactive-managers');
  }

  public activateManager(id, body): Observable<any> {
    return this.http.put(this.baseUrl + `/activate/${id}/manager`, body);
  }
}
