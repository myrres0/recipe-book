import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const isAuthenticated = !!localStorage.getItem('token');
  if (isAuthenticated) {
    return true;
  }
  
  const router = new Router(); // Create an instance of the Router service
  router.navigate(['/login']); // Redirect to the '/login' page
  
  return false;
};
