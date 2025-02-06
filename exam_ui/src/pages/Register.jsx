import { useState } from 'react';
import { useAuth } from '../auth/AuthContext';
import { authenticate, register } from '../api/apiAuthService';
import BeatLoader from 'react-spinners/BeatLoader';

export const Register = () => {
  const { user, userIsAuthenticated, loginUser, logoutUser } = useAuth();
  const [isLoading, setIsLoading] = useState(false);
  const isAuthenticated = userIsAuthenticated();
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    email: '',
  });
  const [error, setError] = useState('');

  const [formErrors, setFormErrors] = useState({
    usernameErr: '',
    passwordErr: '',
    emailErr: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormErrors({
      usernameErr: '',
      passwordErr: '',
      emailErr: '',
    });
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    const submitFormErrors = {};
    formData.username.length < 2 ? (submitFormErrors.usernameErr = 'Username should contain at least 2 letters') : 'as';
    formData.password.length <= 0 ? (submitFormErrors.passwordErr = 'Should include password') : '';
    formData.email.length <= 0 ? (submitFormErrors.emailErr = 'Should include email') : '';

    if (Object.keys(submitFormErrors).length > 0) {
      setFormErrors({ ...formErrors, ...submitFormErrors });
    } else {
      try {
        setIsLoading(true);
        const registerResponse = await register(formData);
        if (registerResponse.status === 201) {
          isAuthenticated ? logoutUser() : null;
          const authenticatedUser = await authenticate(formData.username, formData.password);
          if (authenticatedUser != 'error') {
            loginUser(authenticatedUser);
            setFormData({
              username: '',
              email: '',
              password: '',
            });
          }
        }
      } catch (err) {
        setError(err.response?.data?.message || 'An error occurred during signup.');
      } finally {
        setIsLoading(false);
      }
    }
  };
  return (
    <section className="form_wrap">
      <form onSubmit={handleSubmit}>
        <h2 className="grid__header">Register your account</h2>
        <div className="input_wrap">
          <input type="text" placeholder="Username" name="username" value={formData.username} onChange={handleChange} />
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
        <div className="input_wrap">
          <input
            type="email"
            placeholder="john@gmail.com"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
          {formErrors.emailErr && <p className="error">{formErrors.emailErr}</p>}
        </div>
        <div className="form__button-wrap">
          <button className="button" type="submit">
            Register
          </button>
        </div>
      </form>
      <p className="error">{error}</p>
      {isLoading && (
        <span>
          <BeatLoader />
        </span>
      )}
    </section>
  );
};
