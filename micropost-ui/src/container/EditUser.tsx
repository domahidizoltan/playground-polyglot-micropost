import React from "react";
import { UserForm } from "../component";
import { stateStore, setHeaderTitle } from "../statestore/AppState";
import { RouteComponentProps } from "react-router";
import { Navigation } from "../common/Helpers";
import { RouteParams } from "../common/Types";
import serviceBaseUrl from "../common/Constants";

export default class EditUser extends React.Component<RouteComponentProps<RouteParams>> {
    private endpointBaseUrl = '/users'
    
    componentWillMount() {
        const id = Navigation.getId(this.props)
        const isNew = id === 'add'
        const prefix = isNew ? 'Add' : 'Edit'
        stateStore.dispatch(setHeaderTitle(prefix + " User"))

        if (!isNew) {
            fetch(`${serviceBaseUrl}${this.endpointBaseUrl}/${id}`)
                .then(resp => resp.json())
                .then(data => this.setState(data))
                .catch(console.log);
        }
    } 

    render() {
        return <UserForm {...this.props}
            formData={this.state}
            handleCancel={e => Navigation.navTo(this.endpointBaseUrl, this.props)} 
            submitUrl={this.endpointBaseUrl}
            updateIdFieldName='nickname'
        />
    }

}
