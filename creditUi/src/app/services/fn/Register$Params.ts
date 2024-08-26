import { filter, map, Observable } from "rxjs";
import { RequestBuilder } from "../request-builder";
import { HttpClient, HttpContext, HttpResponse } from "@angular/common/http";
import { RegistrationRequest } from "../models/RegistrationRequest";
import { StrictHttpResponse } from "../Strict-Http-Response";

export interface Register$Params {
    body: RegistrationRequest
}

export function register(http: HttpClient, rootUrl: string, params: Register$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
const rb = new RequestBuilder(rootUrl, register.PATH, 'post');
if (params) {
  rb.body(params.body, 'application/json');
}

return http.request(
  rb.build({ responseType: 'json', accept: 'application/json', context })
).pipe(
  filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
  map((r: HttpResponse<any>) => {
    return r as StrictHttpResponse<{
    }>;
  })
);
}

register.PATH = '/auth/register';