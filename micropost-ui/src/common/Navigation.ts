import { RouteComponentProps } from "react-router"

export class Navigation {
    static navTo(url: string, props: RouteComponentProps) {
        const {history} = props
        if (history) {
            history.push(url)
        }
    }
    
}
