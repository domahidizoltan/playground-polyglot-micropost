import React from "react";
import { Form } from "../component";
import { stateStore, setHeaderTitle } from "../statestore/AppState";

export default class AddUser extends React.Component {
    componentWillMount = () => stateStore.dispatch(setHeaderTitle("Add User"))

    render() {
        return (
            <Form/>
        )
    }
}
