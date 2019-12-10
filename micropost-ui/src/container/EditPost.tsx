import React from "react";
import { PostForm } from "../component";
import { stateStore, setHeaderTitle } from "../statestore/AppState";
import { Navigation } from "../common/Helpers";
import { RouteComponentProps } from "react-router";
import { RouteParams } from "../common/Types";
import serviceBaseUrl from "../common/Constants";

export default class EditPost extends React.Component<RouteComponentProps<RouteParams>> {
    private endpointBaseUrl = '/posts'
    
    componentWillMount() {
        const id = Navigation.getId(this.props)
        const isNew = id == 'add'
        const prefix = isNew ? 'Add' : 'Edit'
        stateStore.dispatch(setHeaderTitle(prefix + " Post"))

        if (!isNew) {
            fetch(`${serviceBaseUrl}${this.endpointBaseUrl}/${id}`)
                .then(resp => resp.json())
                .then(data => this.setState(data))
                .catch(console.log);
        }
    } 
    render() {
        return <PostForm {...this.props}
            formData={this.state}
            handleCancel={e => Navigation.navTo(this.endpointBaseUrl, this.props)} 
            submitUrl={this.endpointBaseUrl}
            updateIdFieldName='postId'
        />
    }

}
