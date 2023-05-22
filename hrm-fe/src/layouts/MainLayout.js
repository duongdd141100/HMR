import { NavLink } from "react-router-dom";

export default function MainLayout({ header, children }) {
    return (
        <div>
            <div className="header">
                {header.map(item => <NavLink key={item.slug} to={item.slug}>{item.label}</NavLink>)}
            </div>
            <div className="content">
                {children}
            </div>
        </div>
    )
}