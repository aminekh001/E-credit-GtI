import { HttpClient, HttpContext, HttpResponse } from "@angular/common/http";
import { AuthenticationRequest } from "../../pages/login/AuthenticationRequestInterface";
import { RequestBuilder } from "../request-builder";
import { StrictHttpResponse } from "../Strict-Http-Response";
import { AuthenticationResponse } from "../models/authentication-response";
import { filter, map, Observable } from "rxjs";

export interface Authenticate$Params {
    body: AuthenticationRequest
}

export function authenticate(http: HttpClient, rootUrl: string, params: Authenticate$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthenticationResponse>> {
const rb = new RequestBuilder(rootUrl, authenticate.PATH, 'post');
if (params) {
  rb.body(params.body, 'application/json');
}

return http.request(
  rb.build({ responseType: 'json', accept: 'application/json', context })
).pipe(
  filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
  map((r: HttpResponse<any>) => {
    return r as StrictHttpResponse<AuthenticationResponse>;
  })
);
}

authenticate.PATH = '/auth/authenticate';