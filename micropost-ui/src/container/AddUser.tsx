import React from "react";
import { UserForm } from "../component";
import { stateStore, setHeaderTitle } from "../statestore/AppState";
import { RouteComponentProps } from "react-router";
import { Navigation } from "../common/Navigation";

export default class AddUser extends React.Component<RouteComponentProps> {
    componentWillMount = () => stateStore.dispatch(setHeaderTitle("Add User"))

    render() {
        return <UserForm {...this.props}
            handleCancel={e => Navigation.navTo("/users", this.props)} 
            endpointBaseUrl="/users"
        />
    }
}
