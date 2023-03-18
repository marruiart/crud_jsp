class EventEmitter {
    constructor() {
        this.eventMap = {};
    }

    on(eventName, callback) {
        if (this.eventMap[eventName])
            this.eventMap[eventName].push(callback);
        else
            this.eventMap[eventName] = [callback];
    }

    emit(eventName, detailObject = null) {
        if (!this.eventMap[eventName]) return;
        for (const callback of this.eventMap[eventName])
            callback(detailObject);
    }
}

class SearchBoxComponent {
    constructor(searchBox) {
        this.element = searchBox;
        [this.term, this.setTerm] = State.useState('', this.termChanged.bind(this));
        this.timeoutRef = null;

        this.element.addEventListener('input', ((event) => {
            this.setTerm(event.target.value);
            this.render();
        }).bind(this));

        this.eventEmitter = new EventEmitter();
        this.eventEmitter.on('clean', () => {
            let search = window.location.href.split("&select=")[1];
            if (search)
                window.location = `${window.location.href.split("?")[0]}?select=${search}`;
            else
                window.location = window.location.href.split("?")[0];
        });
    }

    termChanged() {
        if (!this.term.value) {
            this.eventEmitter.emit('clean');
            return;
        }
        if (this.timeoutRef)
            clearTimeout(this.timeoutRef);
        this.timeoutRef = setTimeout(
            (() => this.search(this.term.value)).bind(this), 500
        );
    }

    search = async (debouncedTerm) => {
        if (!debouncedTerm) return;
        let search = window.location.search;
        let urlParams = new URLSearchParams(search);
        search = urlParams.get("select");
        window.location.search = `filter=${debouncedTerm}` + (search ? `&select=${search}` : "");
    }

    render() {
        this.element.value = this.term.value;
    }
}

class State {
    static useState(initialValue, cb) {
        let state = { value: initialValue };
        return [state, (newValue) => {
            const oldValue = state.value;
            state.value = newValue;
            cb(oldValue);
        }];
    }
}

window.onload = () => {
    let href = window.location.href;
    if (href.includes("groups.jsp") || href.includes("students.jsp") || href.includes("enroll.jsp")) {
        const SEARCH_BOX = document.querySelector('.search-box');

        SEARCH_BOX.addEventListener('focus', () => {
            const end = SEARCH_BOX.value.length;
            SEARCH_BOX.setSelectionRange(end, end);
        });

        if (SEARCH_BOX.value)
            SEARCH_BOX.focus();

        new SearchBoxComponent(SEARCH_BOX);
    }
    if (href.includes("enroll.jsp")) {
        let selectValue;
        let search = window.location.search;
        let urlParams = new URLSearchParams(search);
        const SELECT_GROUP = document.querySelector('.search-group');
        SELECT_GROUP.value = search ? urlParams.get("select") : 0;

        SELECT_GROUP.addEventListener('change', () => {
            selectValue = SELECT_GROUP.value;
            let splittedSearch = search.split("&")[0].split("?select")[0];
            window.location.search = splittedSearch ? `${splittedSearch}&select=${selectValue}` : `select=${selectValue}`;
        });
    }

}
