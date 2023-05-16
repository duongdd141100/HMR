import { NavLink } from "react-router-dom";

export default function MainLayout({ items }) {
    // console.log(items)
    return (
        items.map(item => <NavLink key={item.slug} to={item.slug}>{item.label}</NavLink>)
    )
}