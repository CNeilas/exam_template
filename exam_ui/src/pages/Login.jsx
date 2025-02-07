import { useAuth } from '../auth/AuthContext';
import { useState } from 'react';
import { authenticate } from '../api/apiAuthService';
import BeatLoader from 'react-spinners/BeatLoader';

export const Login = () => {
  const { user, userIsAuthenticated, loginUser, logoutUser } = useAuth();
  const [isLoading, setIsLoading] = useState(false);
  const isAuthenticated = userIsAuthenticated();
  const [formData, setFormData] = useState({
    username: '',
    password: '',
  });
  const [error, setError] = useState('');

  const [formErrors, setFormErrors] = useState({
    usernameErr: '',
    passwordErr: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormErrors({
      usernameErr: '',
      passwordErr: '',
    });
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleLogout = () => {
    logoutUser();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    const submitFormErrors = {};
    formData.username.length < 2 ? (submitFormErrors.usernameErr = 'Username should contain at least 2 letters') : 'as';
    formData.password.length <= 0 ? (submitFormErrors.passwordErr = 'Should include password') : '';

    if (Object.keys(submitFormErrors).length > 0) {
      setFormErrors({ ...formErrors, ...submitFormErrors });
    } else {
      try {
        setIsLoading(true);
        const authenticatedUser = await authenticate(formData.username, formData.password);
        if (authenticatedUser != 'error') {
          loginUser(authenticatedUser);
        }
      } catch (err) {
        setError('Invalid username or password.');
      } finally {
        setIsLoading(false);
      }
    }
  };

  return (
    <section className="form_wrap">
      {!isAuthenticated && (
        <>
          <form onSubmit={handleSubmit}>
            <h2 className="grid__header">Login</h2>
            <div className="input_wrap">
              <input
                type="text"
                placeholder="Username"
                name="username"
                value={formData.username}
                onChange={handleChange}
              />
              {formErrors.usernameErr && <p className="error">{formErrors.usernameErr}</p>}
            </div>
            <div className="input_wrap">
              <input
                type="password"
                placeholder="Password"
                name="password"
                value={formData.password}
                onChange={handleChange}
              />
              {formErrors.passwordErr && <p className="error">{formErrors.passwordErr}</p>}
            </div>
            <div className="form__button-wrap">
              <button className="button" type="submit">
                Login
              </button>
            </div>
          </form>
          <p className="error">{error}</p>
          <p>
            Don't have an account? <a href="/register">Register here!</a>{' '}
          </p>
          {isLoading && (
            <span>
              <BeatLoader />
            </span>
          )}
        </>
      )}
      {isAuthenticated && (
        <>
          <h2>Already logged in!</h2>
          <button className="button" onClick={handleLogout}>
            Logout
          </button>
        </>
      )}
    </section>
  );
};
