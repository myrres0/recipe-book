


export class ChangePasswordRequest {
    currentPassword: string;
    newPassword: string;
    confirmPassword: string;

    constructor(oldPassword: string, newPassword: string, confirmPassword: string) {
        this.currentPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
}
