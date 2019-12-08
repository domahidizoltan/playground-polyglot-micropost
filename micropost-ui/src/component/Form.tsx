import React from 'react';
import './Form.css'
import { RouteComponentProps } from 'react-router';
import serviceBaseUrl from '../common/Constants';
import { operationResultStateStore, setLastOperationResult, ResultType, 
    OperationResultState, OperationResult } from '../statestore/AppState';
import { Unsubscribe } from '@reduxjs/toolkit';

export interface FormProps extends RouteComponentProps {
    endpointBaseUrl: string,
    handleCancel(event: any): void
}

export default abstract class Form extends React.Component<FormProps> {

    abstract formContent(): JSX.Element;

    componentWillMount() {
        operationResultStateStore.dispatch(setLastOperationResult(undefined))
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleSubmit(event: any) {
        event.preventDefault()

        var data:any = {};
        new FormData(event.target).forEach((value, key) => {data[key] = value});

        this.postData(data);
    }    

    render() {
        return (
            <div>
                <div className="contact-clean">
                    <form onSubmit={this.handleSubmit}>
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

    private postData = (data: any) =>
        fetch(`${serviceBaseUrl}${this.props.endpointBaseUrl}`, {
            method: 'POST',
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
        .catch(console.log);


    private dispatchErrorResult = (error:any) => 
    operationResultStateStore.dispatch(setLastOperationResult({
        type: ResultType.error,
        message: error
    }));

    private dispatchSuccessResult = () => 
        operationResultStateStore.dispatch(setLastOperationResult({
            type: ResultType.success,
            message: 'Saved successfully'
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
            const styleName = result.type == ResultType.success ? 'success' : 'danger'
            const classNames = `alert alert-${styleName}`
            return <div className={classNames}>{result.message}</div>    
        }
        
        return null
    }
}

