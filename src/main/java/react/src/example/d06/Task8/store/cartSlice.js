import { createSlice } from "@reduxjs/toolkit";

const initialState = { cartList : [] };

const cartSlice = createSlice({
    name : "cart",
    initialState,
    reducers : {
        addToCart : (state, action) => {
            // 액션으로부터 받은 제품을 상태에 담기
            // 받은 제품이 cart(상태)에 존재하는지 확인, 존재하면 amount 1 증가, 아니면 cart에 amount : 1로 제품 정보 push
            const item = action.payload;
            let check = false;
            state.cartList.forEach((p) => {
                if (p.id == item.id) {
                    p.amount += 1;
                    check = true;
                }
            })

            if (check == false) {
                state.cartList.push({...item, amount : 1});
            }

            // item.amount = 1;
            // if (check == false) { state.cart.push(item); }
        },
        removeFromCart : (state, action) => {

        }
    }
});

export default cartSlice.reducer;
export const {addToCart, removeFromCart} = cartSlice.actions;