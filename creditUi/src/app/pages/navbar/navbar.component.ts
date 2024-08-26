import { Component, OnInit } from '@angular/core';
import { Router, RouterModule ,ActivatedRoute  } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  link: string | undefined ;

  roles: string[] = [];
  isLoggedIn: boolean = false;
  isUser: boolean = false;
  isAdmin: boolean = false

  constructor(private router: Router ,private route: ActivatedRoute) {}


  ngOnInit(): void {
    this.link = window.location.pathname;
    console.log(this.link)
    this.updateLoginStatus();
  }

  updateLoginStatus() {
    const rolesString = localStorage.getItem('R');
    const roles = JSON.parse(rolesString!) as number[];
 
    this.isLoggedIn = roles.length > 0;
    if (roles.includes(2)) {
      this.isAdmin=true;
     
    } else if (roles.includes(1)) {
      this.isUser=true;
    }

  }
  logout() {
    
    localStorage.removeItem('token');
    localStorage.removeItem('userId') ;
    localStorage.removeItem('R');

    
    this.router.navigate(['/login'])
  
    
  }

}
