

enum Role{
    USER,
    ADMIN
}

export class User {
    id!: number;
    firstname!: string;
    lastname!: string;
    email!: string;
    password!: string;
    role!: Role;

    constructor(id: number, firstname: string, lastname: string, email: string, password: string, role: Role);
    constructor();

    constructor(id?: number, firstname?: string, lastname?: string, email?: string, password?: string, role?: Role) {
        this.id = id!;
        this.firstname = firstname!;
        this.lastname = lastname!;
        this.email = email!;
        this.password = password!;
        this.role = role!;
    }
}
