import React from 'react';
import './Form.css'
import { RouteComponentProps } from 'react-router';
import serviceBaseUrl from '../common/Constants';
import { operationResultStateStore, setLastOperationResult, ResultType, 
    OperationResultState, OperationResult } from '../statestore/AppState';
import { Unsubscribe } from '@reduxjs/toolkit';
import { Link } from 'react-router-dom';

export interface FormProps extends RouteComponentProps {
    submitUrl: string,
    handleCancel(event: any): void,
    formData?: any,
    updateIdFieldName: string
}

export default abstract class Form extends React.Component<FormProps, any> {

    abstract formContent(): JSX.Element;

    componentWillMount() {
        operationResultStateStore.dispatch(setLastOperationResult(undefined))
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleSubmit(event: any) {
        event.preventDefault()

        var data:any = {};
        new FormData(event.target).forEach((value, key) => {data[key] = value});

        this.saveData(data);
    }    

    render() {
        let updateId = this.getUpdateId()
        return (
            <div>
                <div className="contact-clean">
                    <form onSubmit={this.handleSubmit}>
                        {(updateId) ? <input type="hidden" name="updateId" value={updateId} /> : ''}
                        {this.formContent()}
                        <div className="d-flex w-100 justify-content-between">
                            <div className="w-100"><OperationFeedback/></div>
                            <div className="formButtons">
                                <div className="form-group nav justify-content-end">
                                    <button className="btn btn-secondary" type="submit">Submit</button>
                                    <button className="btn btn-outline-secondary" type="button" onClick={this.props.handleCancel}>Cancel</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
    
    private getUpdateId() {
        let name = this.props.updateIdFieldName
        let props = this.props as any
        if (props.formData) {
            return props.formData[name]
        }
    }

    private saveData(data: any) {
        let method = (data.updateId) ? 'PUT' : 'POST'
        let urlIdPart = (data.updateId) ? `/${data.updateId}` : ''

        fetch(`${serviceBaseUrl}${this.props.submitUrl}${urlIdPart}`, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(data => {
            if (!data.ok) {
                data.text().then(this.dispatchErrorResult);
            }
            else {
                this.dispatchSuccessResult();
            }
        })
        .catch((error) => {
            console.log(error)
            this.dispatchErrorResult(error);
        })
    }


    private dispatchErrorResult(error:any) {
        const message = JSON.parse(error).message
        return operationResultStateStore.dispatch(setLastOperationResult({
            type: ResultType.error,
            message: message
        }));
    }
        
    private dispatchSuccessResult = () => 
        operationResultStateStore.dispatch(setLastOperationResult({
            type: ResultType.success,
            message: <span>Saved. Go back to <Link to={this.props.submitUrl}>the list</Link></span>
        }));

}

class OperationFeedback extends React.Component<any, OperationResultState> {

    storeSubscription?: Unsubscribe
    
    componentWillMount() {
        this.storeSubscription = operationResultStateStore.subscribe(this.forceUpdate.bind(this))
    }

    componentWillUnmount() {
        if (this.storeSubscription) {
            this.storeSubscription()
        }
    }

    render() {
        const {lastResult} = operationResultStateStore.getState()
        if (lastResult) { 
            let result: OperationResult = lastResult!
            const styleName = result.type === ResultType.success ? 'success' : 'danger'
            const classNames = `alert alert-${styleName}`
            return <div className={classNames}>{result.message}</div>    
        }
        
        return null
    }
}

