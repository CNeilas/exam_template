import { Navigate } from 'react-router-dom';

export const SecuredRoute = ({ children }) => {
  const user = localStorage.getItem('user');

  if (!user) {
    return <Navigate to="/login" />;
  }

  try {
    const userRoles = JSON.parse(user).data.rol;

    if (userRoles.some((role) => role === 'ADMIN' || 'USER')) {
      return children;
    } else {
      //TODO unauthorized page
      return <Navigate to="/unauthorized" />;
    }
  } catch (error) {
    console.error('Token Error', error);
  }
};
