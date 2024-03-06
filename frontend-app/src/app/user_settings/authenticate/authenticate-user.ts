

export class AuthenticateUser {
    email!: string;
    password!: string;


    constructor(email: string, password: string);
    constructor();

    constructor(email?: string, password?: string) {
        this.email = email!;
        this.password = password!;
    }
}
