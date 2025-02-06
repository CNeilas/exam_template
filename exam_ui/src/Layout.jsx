import { Navbar } from './components/Navbar';

export const Layout = ({ children }) => {
  return (
    <div className="app">
      <Navbar />
      <main>{children}</main>
    </div>
  );
};
