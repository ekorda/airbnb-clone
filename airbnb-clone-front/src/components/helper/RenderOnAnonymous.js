import UserService from "../../services/UserService";
import { Navigate } from "react-router";

const RenderOnAnonymous = ({ children }) =>
  !UserService.isLoggedIn() ? children : <Navigate to="/landloard"></Navigate>;

export default RenderOnAnonymous;
