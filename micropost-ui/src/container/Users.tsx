import React from "react";
import { PagedList } from "../component";
import { stateStore, setHeaderTitle } from "../statestore/AppState";

export default class Users extends React.Component {
    componentWillMount = () => stateStore.dispatch(setHeaderTitle("Users"))

    render() {
        return (
            <PagedList 
                items={[{title:"user1"}, {title:"user2"}]}
                pages={2} 
              />
        )
    }
}
