import { createSlice } from "@reduxjs/toolkit";

const initialState = {cartItem : [] };

const cartSlice = createSlice({
    name : "cart",
    initialState,
    reducers : {
        addToCart : (state, action) => {
            const newItem = action.payload;
            // 기존에 같은 id를 가진 아이템이 있는지 확인
            const existingItem = state.cartItem.find(item => item.id === newItem.id);

            if (existingItem) {
                // 이미 존재하면 amount 증가
                existingItem.amount += 1;
            } else {
                // 없으면 amount: 1로 초기화해서 추가
                state.cartItem.push({ ...newItem, amount: 1 });
            }
        }
    }
});


export default cartSlice.reducer;
export const {addToCart} = cartSlice.actions;