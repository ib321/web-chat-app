import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ChatService {


  //private baseUrl = 'http://localhost:8085/';
  private baseUrl = 'api/';
  serverUrlEndpoint: string = `${this.baseUrl}msg/`;

  constructor(private http: HttpClient) { }

  public getValueFromServer(accessLevel: string): Observable<any> {
    return this.http.get<any>(this.serverUrlEndpoint+accessLevel);
  }

  // public findByName(name: string): Observable<Book[]> {
  //   return this.http.get<Book[]>(this.endpoints.bookByName + name);
  // }
}
