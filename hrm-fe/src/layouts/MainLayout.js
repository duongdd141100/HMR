import { NavLink } from "react-router-dom";

export default function MainLayout({ header }) {
    // console.log(items)
    return (
        header.map(item => <NavLink key={item.slug} to={item.slug}>{item.label}</NavLink>)
    )
}