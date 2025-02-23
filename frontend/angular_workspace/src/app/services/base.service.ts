import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, tap} from 'rxjs/operators';
// import {ErrorHandler} from "../error-handler";
// import {AppComponent} from '../../components/app.component';


const headers = new HttpHeaders({
  'Content-Type': 'application/json',
  Authorization: 'my-auth-token'
})

const httpOptionsJson = {
  // headers: headers,
  // observe: 'body' as const,
  // observe: 'events' as const,
  observe: 'response' as const,
  responseType: 'json' as const,
};

const httpOptionsText = {
  // headers: headers,
  // observe: 'body' as const,
  // observe: 'events' as const,
  observe: 'response' as const,
  responseType: 'text' as const,
};


@Injectable({
  providedIn: 'root'
})

export class BaseService<T> {
  constructor(protected http: HttpClient) {
//     this.TName = x.name;
    // this.httpOptionsJson.responseType = 'json' as const;
    // this.httpOptionsText.responseType = 'text' as const;
  }

//   protected url = '';
//   protected TName = '';

  protected getUrl(): string {
    return '';
  }

  protected getTypeName(): string {
    return '';
  }


  getAll(): Observable<HttpResponse<T[]>> {
    return this.http.get<T[]>(this.getUrl() + '/all', httpOptionsJson)
      .pipe(
//         tap(_ => this.log('got all ' + this.getTypeName() + 's')),
        tap(response => this.log('RESPONSE:' + (response.body ?? 'got empty response.body'))),
        catchError(BaseService.handleError<T[]>())
      );
  }

  getById(id: number): Observable<HttpResponse<T>> {
    return this.http.get<T>(this.getUrl() + '/byId/' + id, httpOptionsJson)
      .pipe(
        tap(_ => this.log('got ' + this.getTypeName() + ` id=${id}`)),
        catchError(BaseService.handleError<T>())
      );
  }

  delete(id: number): Observable<HttpResponse<string>> {
    return this.http.delete(this.getUrl() + '/byId/' + id, httpOptionsText).pipe(
      tap(response => this.log(response.body ?? 'got empty response.body')),
      catchError(BaseService.handleError<string>())
    );
  }

  create(t: T): Observable<HttpResponse<string>> {
    return this.http.post(this.getUrl(), t, httpOptionsText).pipe(
      tap(response => this.log(response.body ?? 'got empty response.body')),
      catchError(BaseService.handleError<string>())
    );
  }

  update(t: T): Observable<HttpResponse<string>> {
    return this.http.put(this.getUrl(), t, httpOptionsText).pipe(
      tap(response => this.log(response.body ?? 'got empty response.body')),
      catchError(BaseService.handleError<string>())
    );
  }

  log(message: string): void {
    console.log(message);
  }

  static handleError<T>(result?: HttpResponse<T>): (error: any) => Observable<HttpResponse<T>> {
    return (error: any): Observable<HttpResponse<T>> => {
      console.error(error.error, error);

      // Let the app keep running by returning an empty result.
      return of(result as HttpResponse<T>);
    };
  }

}
