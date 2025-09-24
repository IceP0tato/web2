import { createSlice } from "@reduxjs/toolkit";

const initialState = {items : {}};

const cartSlice = createSlice({
    name : "cart",
    initialState,
    reducers : {
        addToCart : (state, action) => {
            const item = action.payload;
            
        }
    }
});

export default cartSlice.reducer;
export const {addToCart} = cartSlice.actions;