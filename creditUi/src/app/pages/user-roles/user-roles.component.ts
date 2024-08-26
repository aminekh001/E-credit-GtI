import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../services/models/userRole';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-roles',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-roles.component.html',
  styleUrl: './user-roles.component.css'
})
export class UserRolesComponent {
  users: User[] = [];
  filteredUsers: User[] = [];
  searchFirstName: string = '';
  searchLastName: string = '';

  private baseUrl = 'http://localhost:8222/api/v1/user';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getAllUsers().subscribe((data: User[]) => {
      this.users = data;
      this.filteredUsers = data; // Initialize filteredUsers with all users
    });
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/find/all`);
  }

  changeRole(id: number): void {
    this.http.post<void>(`${this.baseUrl}/responsable/${id}`, {}).subscribe(() => {
      this.ngOnInit(); // Reload users after role change
    });
  }

  filterUsers():  void {
    this.filteredUsers = this.users.filter(user =>
      (user.firstname ? user.firstname.toLowerCase().includes(this.searchFirstName.toLowerCase()) : false) &&
      (user.lastname ? user.lastname.toLowerCase().includes(this.searchLastName.toLowerCase()) : false)
    );
  }

}
