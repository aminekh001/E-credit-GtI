import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const token = localStorage.getItem('token');

  if (token) {
    try {
      
      // Check if roles exist or meet certain conditions
      if (token.length > 0) {
        return true;
      }
    } catch (error) {
      console.error('Error parsing token:', error);
    }
  }
  
  // Redirect to login if no token or invalid token
  router.navigate(['/login']);
  return false;
};
