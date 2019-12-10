import { RouteComponentProps } from "react-router"
import { RouteParams } from "./Types"

export class Navigation {
    static navTo(url: string, props: RouteComponentProps) {
        const {history} = props
        if (history) {
            history.push(url)
        }
    }
    
    static getId = (props: RouteComponentProps<RouteParams>) => props.match.params.id
}

interface Link {
    rel: string,
    href: string
}

export class Helper {
    static getLinksAsMap = (node: any): Map<string, string> => 
        new Map(node.links.map((link:Link) => [link.rel, link.href] as [string, string]))
}