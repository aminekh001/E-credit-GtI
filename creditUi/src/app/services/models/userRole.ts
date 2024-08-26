 export interface User {
    id: number;
    firstname: string;
    lastname: string;
    roles: { id: number; name: string }[];
  }