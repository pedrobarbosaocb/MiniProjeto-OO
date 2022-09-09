package controle;

import java.util.ArrayList;

import modelo.Amigo;
import modelo.Despesa;
import modelo.Usuario;

/**
 * Classe que realiza algumas operações de busca na lista de amigos
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see ControleDados
**/
public class ControleAmigos {
		private ArrayList<Amigo> amigos;
		
		/**
		 * Construtor ControleAmigos
		 * 
		 * @param controleDados ControleDados
		 * **/
		public ControleAmigos(ControleDados controleDados) {
			this.amigos = controleDados.getAmigos();
		}
		
		public ArrayList<Amigo> getAmigos(){
			return amigos;
		}
		
		/**
		 * Método percorre a lista de amigos e verifica se o id do amigo é igual ao fornecido como parâmetro
		 * 
		 * @param id int
		 * @return Amigo com o id igual ao fornecido, ou null caso não encontre nenhum amigo 
		 */
		public Amigo getAmigo(int id) {
			for(int i = 0; i < amigos.size(); i++) {
				if(amigos.get(i).getId() == id) {
					return amigos.get(i);
				}
			}
			return null;
		}
		
		/**
		 * Método percorre a lista de amigos e verifica se o email do amigo é igual ao fornecido como parâmetro
		 * 
		 * @param email String
		 * @return Amigo com o email igual ao fornecido, ou null caso não encontre nenhum
		 */
		public Amigo getAmigo(String email) {
			for(int i = 0; i<amigos.size(); i++) {
				if(amigos.get(i).getEmail().equals(email)) {
					return amigos.get(i);
				}
			}
			return null;
		}
		
		/**
		 * Método fornece a lista de usuarios dos quais essa instancia de Amigo é amigo
		 * 
		 * @param id int
		 * @return ArrayList(Usuario) com os usuarios que esse amigo possui como amigo
		 */
		public ArrayList<Usuario> getUsuariosAmigo(int id) {
			return getAmigo(id).getAmigosDe();
		}
		
		/**
		 * Método fornece a lista de despesas das quais essa instancia de Amigo é devedor
		 * 
		 * @param id int
		 * @return ArrayList(Despesa) com as despesas que esse amigo possui como devedor
		 */
		public ArrayList<Despesa> getDebitosAmigo(int id) {
			return getAmigo(id).getDebitos();
		}
		
		/**
		 * Método fornece a lista de despesas das quais essa instancia de Amigo é credor
		 * 
		 * @param id int
		 * @return ArrayList(Despesa) com as despesas que esse amigo possui como credor
		 */
		public ArrayList<Despesa> getCreditosUsuario(int id) {
			return getAmigo(id).getCreditos();
		}
		
		/**
		 * Método percorre a lista de amigos e verifica se o email fornecido já pertence à um amigo
		 * 
		 * @param email String
		 * @return true caso já exista um amigo com esse email, false caso contrario
		 */
		public boolean amigoExiste(String email) {
			for(int i = 0; i<amigos.size(); i++) {
				if(amigos.get(i).getEmail().equals(email)) {
					return true;
				}
			}
			return false;
		}
}
