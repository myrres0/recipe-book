import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";


export class JwtInterceptor implements HttpInterceptor{
    
    constructor(){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        

        //get the token from the local storage
        const jwtToken = localStorage.getItem('token');

        //if the token is not null, add it to the request header
        if(jwtToken){
            req = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${jwtToken}`
                }
            });
            
            //check if token is expired and delete it if it is
            const tokenPayload = JSON.parse(atob(jwtToken.split('.')[1]));
            const expirationTime = tokenPayload.exp * 1000;
            console.log('Token expiration time:', new Date(expirationTime).toLocaleString());
            if (expirationTime < Date.now()) {
                localStorage.removeItem('token');
            }
        }
        return next.handle(req);
    }
}
