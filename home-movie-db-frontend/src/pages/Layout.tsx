import { Outlet } from "react-router";
import { Header } from "../components/Header/Header";
import { Footer } from "../components/Footer/Footer";

const Layout: React.FC = () => {
  return (
    <div className="bg-[#F0EFFF]">
      <Header />
      <Outlet />
      <Footer />
    </div>
  );
};

export default Layout;

