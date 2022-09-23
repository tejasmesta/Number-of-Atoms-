class Solution {
    public String countOfAtoms(String formula) {
        int n = formula.length();
        
        Stack<Map<String,Integer>> st = new Stack<>();
        
        st.push(new TreeMap<>());
        
        for(int i=0;i<n;)
        {
            char c = formula.charAt(i);
            
            if(c=='(')
            {
                st.push(new TreeMap<>());
                i++;
            }
            else if(c==')')
            {
                Map<String,Integer> m = st.pop();
                
                int start = ++i;                
                int mul = 1;
                
                while(i<n && Character.isDigit(formula.charAt(i)))  i++;
                
                if(i>start)
                {
                    mul = Integer.parseInt(formula.substring(start,i));
                }
                
                for(String s:m.keySet())
                {
                    int v = m.get(s);
                    st.peek().put(s,st.peek().getOrDefault(s,0)+v*mul);
                }
            }
            else
            {
                int start = i++;
                
                while(i<n && Character.isLowerCase(formula.charAt(i)))  i++;
                
                String name = formula.substring(start,i);
                
                start = i;
                
                while(i<n && Character.isDigit(formula.charAt(i)))  i++;
                
                int mul = i>start ? Integer.parseInt(formula.substring(start,i)) : 1;
                
                st.peek().put(name,st.peek().getOrDefault(name,0)+mul);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(String s:st.peek().keySet())
        {
            sb.append(s);
            int mul = st.peek().get(s);
            if(mul>1)   sb.append(mul+"");
        }
        
        return sb.toString();
    }
}
