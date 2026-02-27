import {useState, useEffect, useCallback} from "react"

const MyInput = () => {
    const [name, setName] = useState();

    useEffect(() => {
        console.log("Hello")
    }, [])
    return(
        <>
            <input type="text" onChange={(event) => setName(event.target.value)} />
            <div>
                <p>Conteudo: {name}</p>
            </div>
        </>
    )
}

export default MyInput;