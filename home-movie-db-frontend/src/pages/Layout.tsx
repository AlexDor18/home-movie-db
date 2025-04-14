import { Outlet } from "react-router";
import { Header } from "../components/Header/Header";

const Layout: React.FC = () => {
  return (
    <div className="bg-[#F0EFFF]">
      <Header />
      <Outlet />
      <footer className="layout-footer">
        <p>Footer content</p>
      </footer>
    </div>
  );
};

export default Layout;

