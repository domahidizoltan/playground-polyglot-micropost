import React from 'react';
import Form from "./Form";

export default class PostForm extends Form {

    formContent(): JSX.Element {
        const data = this.props.formData || {
            userNickname: undefined,
            content: undefined,
            statistics: undefined
        }

        return (
            <div>
                <div className="form-group">
                    <input className="form-control" type="text" 
                        name="userNickname" placeholder="nickname"
                        defaultValue={data.userNickname}
                    />
                </div>
                <div className="form-group">
                    <textarea className="form-control" 
                        name="content" placeholder="content"
                        defaultValue={data.content}/>
                </div>
                {this.getStatistics(data.statistics)}
            </div>
        );
    }

    private toggleStats(event: any) {
        const stats = document.getElementById("stats")
        if (stats) {
            event.target.innerHTML = (stats.hidden) ? 'Hide statistics' : 'Show statistics'
            stats.hidden = !stats.hidden
        }
    }

    private getStatistics(stats?: any) {
        if (stats) {
            return (
                <div>
                    <button type="button" className="btn btn-outline-link btn-sm" onClick={this.toggleStats}>Show statistics</button>
                    <pre id="stats" className="card" style={{marginTop:"1rem", padding:"0.5rem"}} hidden><big><code>{JSON.stringify(stats, null, 2)}</code></big></pre>
                </div>
            )
        }
    }

}