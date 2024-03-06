import { UserInfoResponse } from '../user-info-response';

describe('UserInfoResponse', () => {
  it('should create an instance', () => {
    expect(new UserInfoResponse('John', 'Doe', 'john.doe@example.com')).toBeTruthy();
  });
});
