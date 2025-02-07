import { useNavigate } from 'react-router-dom';
import { useAuth } from '../auth/AuthContext';
import { FaHome, FaUser } from 'react-icons/fa';
import { FaGear } from 'react-icons/fa6';

export const Navbar = () => {
  const { user, userIsAuthenticated } = useAuth();
  const isAuthenticated = userIsAuthenticated();
  const navigate = useNavigate();
  const handleNavigate = () => {
    navigate('/login');
  };
  return (
    <div className="nav_wrap">
      <nav>
        {isAuthenticated ? (
          <p>Welcome, {user?.data.preferred_username}!</p>
        ) : (
          <p>
            <button className="button" onClick={handleNavigate}>
              Log In
            </button>
          </p>
        )}
        <ul className="nav__list">
          <li>
            <a href="/">
              <FaHome className="nav__icon" size={24} />
            </a>
          </li>
          <li>
            <a href="/login">
              <FaUser className="nav__icon" size={24} />
            </a>
          </li>
          <li>
            <a className="nav__icon" href="/create">Add Listing</a>
          </li>
        </ul>
      </nav>
    </div>
  );
};
