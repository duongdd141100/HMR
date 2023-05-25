import { NavLink } from "react-router-dom";

function logout() {
    localStorage.setItem("token", "");
    window.location.href = '/';
}

export default function MainLayout({ header, children }) {
    return (
        <div>
            <div className="header">
                {header.map(item => <NavLink key={item.slug} to={item.slug}>{item.label}</NavLink>)}
                <NavLink to='/' onClick={logout}>Logout</NavLink>
            </div>
            <div className="content">
                {children}
            </div>
        </div>
    )
}